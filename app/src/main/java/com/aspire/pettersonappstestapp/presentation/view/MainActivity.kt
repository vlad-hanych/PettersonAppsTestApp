package com.aspire.pettersonappstestapp.presentation.view

import android.content.Intent
import android.os.Bundle
import com.aspire.pettersonappstestapp.R
import com.aspire.pettersonappstestapp.presentation.BreedsAdapter
import com.aspire.pettersonappstestapp.presentation.internal.di.HasComponent
import com.aspire.pettersonappstestapp.presentation.internal.di.components.DaggerDataComponent
import com.aspire.pettersonappstestapp.presentation.internal.di.components.DataComponent

class MainActivity : BaseActivity(), HasComponent<DataComponent> {
    private lateinit var dataComponent: DataComponent

    private lateinit var breedsAdapter: BreedsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        this.initializeInjector()

        if (savedInstanceState == null)
            addFragment(R.id.content_framLay_activMain, DataFragment())
    }

    fun initializeInjector() {
        this.dataComponent = DaggerDataComponent.builder()
            .appComponent(applicationComponent)
            .activityModule(activityModule)
            .build()
    }

    override fun getComponent(): DataComponent {
        return dataComponent
    }

    fun share (breed: String,
               country: String,
               origin: String,
               coat: String,
               pattern: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, Share.Builder
                .breed(breed)
                .country(country)
                .origin(origin)
                .coat(coat)
                .pattern(pattern)
                .build())
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    class Share private constructor () {
        companion object Builder {
            private var breed: String? = null
            private var country: String? = null
            private var origin: String? = null
            private var coat: String? = null
            private var pattern: String? = null

            fun breed(value: String) = apply { breed = value }
            fun country(value: String) = apply { country = value }
            fun origin(value: String) = apply { origin = value }
            fun coat(value: String) = apply { coat = value }
            fun pattern(value: String) = apply { pattern = value }

            fun build(): String {
                var message = ""

                if (breed!!.isEmpty()) {
                    throw IllegalStateException(
                            "Breed can not be empty!")
                }
                else {
                    message += "Take a look at: $breed"
                }

                if (country!!.isNotEmpty())
                    message += ", from country: $country"

                if (origin!!.isNotEmpty())
                    message += ", origin: $origin"

                if (coat!!.isNotEmpty())
                    message += ", has coat: $coat"

                if (pattern!!.isNotEmpty())
                    message += ", with pattern: $pattern."

                return message
            }
        }
    }
}
