package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.QuizCardBinding

class GameAdapter(var questions: List<Question>) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = QuizCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }
    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val question = questions[position]
        holder.binding.quizDescription.text = question.questionText
        holder.binding.answerTextView.text = question.correctAnswer

        holder.binding.quizDescription.visibility = View.VISIBLE
        holder.binding.answerTextView.visibility = View.GONE

        var isBackVisible = false

        holder.itemView.setOnClickListener {
            if (!isBackVisible) {
                // Hide question, show answer
                holder.binding.quizDescription.animate().setDuration(300).alpha(0f).rotationY(90f).withEndAction {
                    holder.binding.quizDescription.visibility = View.INVISIBLE
                    holder.binding.answerTextView.visibility = View.VISIBLE
                    holder.binding.answerTextView.alpha = 0f
                    holder.binding.answerTextView.rotationY = -90f
                    holder.binding.answerTextView.animate().alpha(1f).rotationY(0f).setDuration(300).start()
                }.start()
                isBackVisible = true
            } else {
                // Hide answer, show question
                holder.binding.answerTextView.animate().setDuration(300).alpha(0f).rotationY(90f).withEndAction {
                    holder.binding.answerTextView.visibility = View.INVISIBLE
                    holder.binding.quizDescription.visibility = View.VISIBLE
                    holder.binding.quizDescription.alpha = 0f
                    holder.binding.quizDescription.rotationY = -90f
                    holder.binding.quizDescription.animate().alpha(1f).rotationY(0f).setDuration(300).start()
                }.start()
                isBackVisible = false
            }
        }
    }

    override fun getItemCount() = questions.size

    class GameViewHolder(val binding: QuizCardBinding) : RecyclerView.ViewHolder(binding.root)
}
