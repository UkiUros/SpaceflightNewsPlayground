package rs.fourexample.spaceflightnews.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import rs.fourexample.spaceflightnews.databinding.ItemArticleBinding
import rs.fourexample.spaceflightnews.model.Article


class ArticleAdapter :
    ListAdapter<Article, ArticleAdapter.ArticleViewHolder>(ArticleDiffCallback()) {

    var callback: ArticleAdapter.IArticleCallback? = null

    interface IArticleCallback {
        fun onItemClick(url: String)
    }

    inner class ArticleViewHolder(val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = getItem(position)

        holder.binding.textViewTitle.text = article.title

        Picasso.get()
            .load(article.imageUrl)
            .into(holder.binding.imageView)

        holder.itemView.setOnClickListener {
            callback?.onItemClick(getItem(holder.adapterPosition).articleUrl)
        }
    }
}

class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

}
