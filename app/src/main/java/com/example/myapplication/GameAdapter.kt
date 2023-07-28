package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.QuizCardBinding
import android.widget.TextView

class GameAdapter(var questions: List<Question>) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = QuizCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val question = questions[position]
        holder.binding.quizDescription.text = question.questionText

        holder.binding.choicesContainer.removeAllViews()

        val allAnswers = question.incorrectAnswers.toMutableList()
        allAnswers.add(question.correctAnswer)
        allAnswers.shuffle()

        allAnswers.forEach { choice ->
            val choiceView = LayoutInflater.from(holder.itemView.context)
                .inflate(R.layout.choice_item, holder.binding.choicesContainer, false) as TextView
            choiceView.text = choice
            holder.binding.choicesContainer.addView(choiceView)
        }
        holder.binding.answerTextView.text = question.correctAnswer

        // Reset the state of the card to its default
        resetCardState(holder)

        var isBackVisible = false
        holder.binding.root.setOnClickListener {
            if (!isBackVisible) {
                holder.binding.choicesContainer.animate().rotationY(90f)
                    .withEndAction {
                        holder.binding.choicesContainer.visibility = View.INVISIBLE
                        holder.binding.answerTextView.visibility = View.VISIBLE
                        holder.binding.answerTextView.rotationY = -90f
                        holder.binding.answerTextView.animate().rotationY(0f).start()
                    }.start()
                isBackVisible = true
            } else {
                holder.binding.answerTextView.animate().rotationY(90f)
                    .withEndAction {
                        holder.binding.answerTextView.visibility = View.INVISIBLE
                        holder.binding.choicesContainer.visibility = View.VISIBLE
                        holder.binding.choicesContainer.rotationY = -90f
                        holder.binding.choicesContainer.animate().rotationY(0f).start()
                    }.start()
                isBackVisible = false
            }
        }
    }

    private fun resetCardState(holder: GameViewHolder) {
        holder.binding.choicesContainer.rotationY = 0f
        holder.binding.choicesContainer.visibility = View.VISIBLE

        holder.binding.answerTextView.rotationY = 0f
        holder.binding.answerTextView.visibility = View.INVISIBLE
    }

    override fun getItemCount() = questions.size

    class GameViewHolder(val binding: QuizCardBinding) : RecyclerView.ViewHolder(binding.root)
}