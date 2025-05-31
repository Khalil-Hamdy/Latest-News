package com.khalil.latestnews.ui.articledetails.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khalil.latestnews.databinding.FragmentArticleDetailsBinding
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.khalil.latestnews.R
import com.khalil.latestnews.doman.model.Article
import com.khalil.latestnews.ui.articledetails.viewmodel.ArticleDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArticleDetailsFragment : Fragment() {

    private var _binding: FragmentArticleDetailsBinding? = null
    private val binding get() = _binding!!
    private var isFavorite = false
    private val args: ArticleDetailsFragmentArgs by navArgs()
    private val articleDetailsViewModel: ArticleDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleDetailsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = args.article
        with(binding) {
            textTitle.text = article.title
            textDate.text = article.publishedAt
            textDesc.text = article.description
            isFavorite = article.isFavorite
            Glide.with(requireContext())
                .load(article.urlToImage)
                .into(imageNews)
            textAuthor.text = article.author
        }
        binding.iconMenu.setOnClickListener {
            showPopupMenu(it)
        }
        binding.iconBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showPopupMenu(anchor: View) {
        val popup = PopupMenu(requireContext(), anchor)
        popup.menuInflater.inflate(R.menu.action_bar_menu, popup.menu)
        try {
            val fields = popup.javaClass.declaredFields
            for (field in fields) {
                if ("mPopup" == field.name) {
                    field.isAccessible = true
                    val menuPopupHelper = field.get(popup)
                    val classPopupHelper = Class.forName(menuPopupHelper.javaClass.name)
                    val setForceIcons = classPopupHelper.getMethod("setForceShowIcon", Boolean::class.javaPrimitiveType)
                    setForceIcons.invoke(menuPopupHelper, true)
                    break
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val favItem = popup.menu.findItem(R.id.actionFav)
        favItem.title = if (isFavorite) "Remove from favorite" else "Add to favorite"
        favItem.icon = ContextCompat.getDrawable(requireContext(),
            if (isFavorite) R.drawable.ic_heart_red else R.drawable.ic_favorite)

        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.actionShare -> {
                    shareArticle(args.article)
                    true
                }

                R.id.actionFav -> {
                    articleDetailsViewModel.toggleFavorite(args.article)
                    toggleFavorite()
                    true
                }

                else -> false
            }
        }
        popup.show()
    }

    private fun shareArticle(article: Article) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, article.title)
        intent.putExtra(Intent.EXTRA_TEXT, article.url)
        startActivity(Intent.createChooser(intent, "Share via"))
    }

    private fun toggleFavorite() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                articleDetailsViewModel.isFavorite.collect { isFavorite2 ->
                    isFavorite = isFavorite2
                }
            }
        }
    }

}