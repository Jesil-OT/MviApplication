package com.jesil.projectmvi.mviapplication.utils

interface EntityMapper<Entity, DomainModel> {

   fun mapFromEntity(entity: Entity) : DomainModel

   fun mapToEntity(domainModel: DomainModel) : Entity
}