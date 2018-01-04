package eu.kanade.tachiyomi.data.sync.protocol.models

import eu.kanade.tachiyomi.data.sync.protocol.models.common.SyncEntity

class SyncReport {
    // Used when building/applyin report
    @Transient
    var lastId = 0L
    @Transient
    var tmpGen = IntermediaryGenSyncReport(this)
    @Transient
    var tmpApply = IntermediaryApplySyncReport(this)
    
    var deviceId: String = ""

    var entities: MutableList<SyncEntity<*>> = mutableListOf()

    // Sync all entities from this date
    var from: Long = -1 //Datetime in millis since epoch in UTC

    // Sync all entities up to this date
    var to: Long = -1 //Datetime in millis since epoch in UTC

    inline fun <reified M : SyncEntity<*>> findEntities()
        = entities.asSequence().filterIsInstance<M>()

    inline fun <reified M : SyncEntity<*>> findEntity(filter: (M) -> Boolean): M?
        = findEntities<M>().find(filter)
}