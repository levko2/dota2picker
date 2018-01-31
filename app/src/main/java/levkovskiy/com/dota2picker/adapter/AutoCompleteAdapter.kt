package levkovskiy.com.dota2picker.adapter

import android.content.Context
import android.widget.ArrayAdapter
import levkovskiy.com.dota2picker.model.DataResponse

class AutoCompleteAdapter(context: Context?, resource: Int, objects: Array<out DataResponse.Hero>?) : ArrayAdapter<DataResponse.Hero>(context, resource, objects) {

}