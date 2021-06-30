import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.marcin.samplecleanarch.MainActivity
import com.marcin.samplecleanarch.MainScreenStateIdlingResource
import com.marcin.samplecleanarch.MainViewModel
import com.marcin.samplecleanarch.R
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

      @get:Rule
      val testRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

      private lateinit var viewModel: MainViewModel

      @Test
      fun activity_gets_drawn_correct() {
            viewModel = testRule.activity.viewModel

            onView(withId(R.id.progress)).check(matches(isDisplayed()))
            onView(withId(R.id.tryAgainButton)).check(matches(not(isDisplayed())))

            val stateIdlingResource = MainScreenStateIdlingResource()

            IdlingRegistry.getInstance().register(stateIdlingResource)
            testRule.runOnUiThread {
                  viewModel.state.observe(testRule.activity, stateIdlingResource)
            }

            onView(withId(R.id.progress)).check(matches(not(isDisplayed())))

            IdlingRegistry.getInstance().unregister(stateIdlingResource)
            testRule.runOnUiThread {
                  viewModel.state.removeObserver(stateIdlingResource)
            }
      }
}