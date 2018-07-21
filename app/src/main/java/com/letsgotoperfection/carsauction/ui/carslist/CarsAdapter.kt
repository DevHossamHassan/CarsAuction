package com.letsgotoperfection.chillouttime.ui.movies

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.letsgotoperfection.chillouttime.R
import com.letsgotoperfection.chillouttime.models.Movie
import com.letsgotoperfection.chillouttime.utils.loadUrl
import kotlinx.android.synthetic.main.item_movie.view.*


/**
 * @author hossam.
 */
class MoviesAdapter(private val presenter: MoviesPresenter, private val itemClick: (Movie) -> Unit)
    : RecyclerView.Adapter<MoviesAdapter.HotTracksListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotTracksListHolder {

        return HotTracksListHolder((
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_movie, parent, false)))
    }

    override fun onBindViewHolder(holder: HotTracksListHolder, position: Int) {
        holder.bind(presenter.getExistedMovies()[position], itemClick)
    }

    override fun getItemCount(): Int {
        return presenter.getMoviesCount()
    }

    class HotTracksListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie, itemClick: (Movie) -> Unit) {
            itemView.setOnClickListener { itemClick(movie) }
            itemView.tvMovieTitle.text = movie.title
            try {
                itemView.imgMovie.loadUrl(movie.poster_path, itemView.tvMovieTitle)
            } catch (e: Exception) {
                Log.d(this.javaClass.canonicalName, e.message)
            }
        }

//        private fun resetColors() {
//            itemView.tvMovieTitle.setBackgroundColor(swatch!!.getRgb())
//            itemView.tvMovieTitle.setTextColor(swatch!!.getTitleTextColor())
//        }
//
//        private fun applyColors(swatch: Palette.Swatch?) {
//            if (swatch != null) {
//                itemView.tvMovieTitle.setBackgroundColor(swatch!!.getRgb())
//                itemView.tvMovieTitle.setTextColor(swatch!!.getTitleTextColor())
//            }
//        }
    }
}
