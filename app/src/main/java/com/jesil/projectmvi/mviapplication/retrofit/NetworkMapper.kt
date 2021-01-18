package com.jesil.projectmvi.mviapplication.retrofit

import com.jesil.projectmvi.mviapplication.model.Blog
import com.jesil.projectmvi.mviapplication.utils.EntityMapper
import javax.inject.Inject

class NetworkMapper @Inject constructor(

): EntityMapper<BlogNetworkEntity, Blog> {
    override fun mapFromEntity(entity: BlogNetworkEntity): Blog {
        return Blog(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            image = entity.image,
            category = entity.category
        )
    }

    override fun mapToEntity(domainModel: Blog): BlogNetworkEntity {
        return BlogNetworkEntity(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            image = domainModel.image,
            category = domainModel.category
        )
    }

    fun mapFromEntityList(entityList : List<BlogNetworkEntity>) : List<Blog>{
        return entityList.map { mapFromEntity(it) }
    }
}