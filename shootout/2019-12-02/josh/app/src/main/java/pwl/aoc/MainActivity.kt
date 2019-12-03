package pwl.aoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import pwl.aoc.databinding.ActivityMainBinding
import java.util.*
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val random: Random = Random(System.currentTimeMillis())
    private var day = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.answerLabel.visibility = View.INVISIBLE
        binding.nextButton.visibility = View.INVISIBLE
    }

    fun buttonTapped(view: View) {
        binding.answerLabel.visibility = View.VISIBLE
        try {
            val clazz = Class.forName("pwl.aoc.day$day.Day$day")
            val instance: AoCDay = clazz.newInstance() as AoCDay
            val used = mutableSetOf<String>()
            object: CountDownTimer(1000L * (day + 4), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    var index = abs(random.nextInt()) % CrunchText.TEXT.size
                    var text = CrunchText.TEXT[index]
                    while (used.contains(text)) {
                        index = abs(random.nextInt()) % CrunchText.TEXT.size
                        text = CrunchText.TEXT[index]
                    }
                    used.plus(text)
                    binding.answerLabel.text = text
                }

                override fun onFinish() {
                    binding.answerLabel.text = "Answer:\n${instance.doTheDay()}"
                    binding.bigButton.isEnabled = false
                    binding.bigButton.alpha = 0.5F
                    binding.nextButton.visibility = View.VISIBLE
                    binding.nextButton.isEnabled = true
                }
            }.start()
        } catch (e: ClassNotFoundException) {
            binding.dayLabel.text = "Day 1"
            binding.nextButton.visibility = View.INVISIBLE
            binding.nextButton.isEnabled = true
            binding.nextButton.alpha = 1.0F
            binding.answerLabel.visibility = View.INVISIBLE
        }
    }

    fun nextTapped(view: View) {
        day += 1
        binding.bigButton.isEnabled = true
        binding.dayLabel.text = "Day $day"
        binding.bigButton.isEnabled = true
        binding.bigButton.alpha = 1.0F
        binding.nextButton.isEnabled = false
        binding.nextButton.alpha = 0.5F
        binding.answerLabel.visibility = View.INVISIBLE
    }
}

