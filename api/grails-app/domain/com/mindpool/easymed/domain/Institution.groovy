package com.mindpool.easymed.domain

import org.joda.time.DateTime

/**
 * Created by federicobarreraoro on 5/1/16.
 */
class Institution {

    String name
    DateTime creationDate

    static mapping = {
        table 'institution'
        name column: 'name'
        creationDate column: 'creation_date'
    }



}
