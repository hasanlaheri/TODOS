package com.hasan.todos.di

import android.content.Context
import androidx.room.Room
import com.hasan.todos.data.TodoDataBaseDao
import com.hasan.todos.data.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesTodoDao(todoDatabase: TodoDatabase): TodoDataBaseDao
            = todoDatabase.todoDao()


    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): TodoDatabase
            = Room.databaseBuilder(
        context,
        TodoDatabase::class.java,
        "todos_db"
    ).fallbackToDestructiveMigration().build()
}