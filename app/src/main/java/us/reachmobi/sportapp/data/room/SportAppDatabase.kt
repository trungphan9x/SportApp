package us.reachmobi.sportapp.data.room

//@Database(entities = [ScoreEntity::class, SchoolEntity::class], version = 1)
//abstract class SportAppDatabase : RoomDatabase() {
//    abstract fun schoolDao(): SchoolDao
//    abstract fun scoreDao(): ScoreDao
//
//    companion object {
//        // Singleton prevents multiple instances of database opening at the same time.
//        const val DB_VERSION = 1
//        private const val DB_NAME = "nyc_schools_database"
//
//        @Volatile
//        private var INSTANCE: SportAppDatabase? = null
//
//        operator fun invoke(context: Context): SportAppDatabase =
//            INSTANCE ?: synchronized(this) {
//                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
//            }
//
//        private fun buildDatabase(context: Context) =
//            Room.databaseBuilder(context.applicationContext, SportAppDatabase::class.java, DB_NAME)
//                .addMigrations(MIGRATION_1_TO_2)
//                .build()
//
//        private val MIGRATION_1_TO_2 = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//
//            }
//        }
//
//    }
//}