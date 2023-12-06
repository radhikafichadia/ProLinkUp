package com.businesstinder.prolinkup.Matches

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.businesstinder.prolinkup.Chat.ChatActivity
import com.businesstinder.prolinkup.R

/**
 * Created by manel on 10/31/2017.
 */
class MatchesViewHolders(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    var mMatchId: TextView
    var mMatchName: TextView
    var mMatchImage: ImageView
    override fun onClick(view: View) {
        val intent = Intent(view.context, ChatActivity::class.java)
        val b = Bundle()
        b.putString("matchId", mMatchId.text.toString())
        intent.putExtras(b)
        view.context.startActivity(intent)
    }

    init {
        itemView.setOnClickListener(this)
        mMatchId = itemView.findViewById<View>(R.id.Matchid) as TextView
        mMatchName = itemView.findViewById<View>(R.id.MatchName) as TextView
        mMatchImage = itemView.findViewById<View>(R.id.MatchImage) as ImageView
    }
}