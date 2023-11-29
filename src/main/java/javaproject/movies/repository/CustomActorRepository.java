package javaproject.movies.repository;

import javaproject.movies.domain.Actor;

public interface CustomActorRepository {
    void deleteActor(Integer actorId); // Delete

    void updateActor(Integer actorId, Actor actor); // Update
}
