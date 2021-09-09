package com.example.upcomingmovies.data.mappers

interface EntityMapper<Entity, DomainModel> {

    fun mapFromEntry(entity: Entity): DomainModel

    fun mapToEntity(domainModel: DomainModel):Entity

}