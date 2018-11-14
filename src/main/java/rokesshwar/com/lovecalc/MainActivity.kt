package rokesshwar.com.lovecalc

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        var oneHalf: Int = 0
        var secHalf: Int = 0

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgbtn.setOnClickListener {
            val herName = her.text.toString()
            val hisName = his.text.toString()
            val name1 = herName.toCharArray()
            val name2 = hisName.toCharArray()
            for (i in name1) {
                oneHalf = i.toInt()
            }
            for (g in name2) {
                secHalf = g.toInt()
            }
            hidekeypad()



            val ref = FirebaseDatabase.getInstance().getReference("heroes")
            val heroId = ref.push().key.toString()
            val hero = hero(herName, hisName)
            ref.child(heroId).setValue(hero).addOnCompleteListener {
                Toast.makeText(applicationContext, "Love Percentage Calculated SuccessFully", Toast.LENGTH_LONG).show()
                var resultpr = ((oneHalf + secHalf) % 100).toString()
                resultper.text = "$resultpr %"
            }

        }

    }

    private fun hidekeypad() {
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (e: Exception) {
        }

    }
}
