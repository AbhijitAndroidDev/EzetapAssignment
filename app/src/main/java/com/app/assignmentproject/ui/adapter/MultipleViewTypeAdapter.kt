package com.app.assignmentproject.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.assignmentproject.R
import com.app.networklibrary.model.UidataItem

/**
 * Created by abhijit
 */
class MultipleViewTypeAdapter(val mContext: Context, val uidata: List<UidataItem>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    class EditTextTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var etType: EditText = itemView.findViewById<View>(R.id.editext_type_Et) as EditText

    }

    class TextViewTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvType: TextView = itemView.findViewById<View>(R.id.textview_type_Tv) as TextView

    }

    class ButtonTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var btnType: Button = itemView.findViewById<View>(R.id.button_type_Bt) as Button

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view:View

        when (viewType) {
            0 -> {
                view =
                    LayoutInflater.from(parent.context).inflate(R.layout.textview_type, parent, false)
                return TextViewTypeViewHolder(
                    view
                )
            }
            1 -> {
                view =
                    LayoutInflater.from(parent.context).inflate(R.layout.edittext_type, parent, false)
                return EditTextTypeViewHolder(
                    view
                )
            }
            else -> {
                view =
                    LayoutInflater.from(parent.context).inflate(R.layout.button_type, parent, false)
                return ButtonTypeViewHolder(
                    view
                )
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        var uiType = uidata?.get(position)?.uitype
        return when (uiType) {
            "label" -> {
                0
            }
            "edittext" -> {
                1
            }
            else -> {
                2
            }
        }
   }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var uiDataItem = uidata?.get(position)

        when (uiDataItem?.uitype) {
            "label" -> {
                (holder as TextViewTypeViewHolder).tvType.text = uiDataItem?.value
            }
            "edittext" -> {
                (holder as EditTextTypeViewHolder).etType.hint = uiDataItem?.hint
            }
            "button" -> {
                (holder as ButtonTypeViewHolder).btnType.text = uiDataItem?.value
            }
        }
    }

    override fun getItemCount(): Int {
        return uidata?.size?:0
    }
}