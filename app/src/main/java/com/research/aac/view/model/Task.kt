package com.research.aac.view.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.research.aac.BR
import org.threeten.bp.LocalDate
import java.io.Serializable

class Task : BaseObservable(), Serializable {

    var title = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.valid)
        }

    var deadline: LocalDate = LocalDate.now()

    @Bindable
    fun isValid(): Boolean {
        return title.isNotEmpty()
    }
}