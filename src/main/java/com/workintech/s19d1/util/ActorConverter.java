package com.workintech.s19d1.util;

import com.workintech.s19d1.dto.ActorResponse;
import com.workintech.s19d1.entity.Actor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ActorConverter {



    public static List<ActorResponse> convertActorCollectionToActorResponseCollection(Collection<Actor> actors) {

        List<ActorResponse> actorResponses = new ArrayList<>();
        for(Actor actor: actors) {
           actorResponses.add(convertActorToActorResponse(actor));
        }
        return actorResponses;
    }



    public static ActorResponse convertActorToActorResponse(Actor foundActor) {

        return new ActorResponse(
                foundActor.getId(),
                foundActor.getFirstName(),
                foundActor.getLastName(),
                foundActor.getBirthDate(),
                foundActor.getMovies()
        );
    }



    public static ActorResponse convertActorResponseBodyToActorResponse(Actor savedActor) {

        return convertActorToActorResponse(savedActor);

//        return new ActorResponse(savedActor.getId(), savedActor.getFirstName(),
//                savedActor.getLastName(), savedActor.getBirthDate(), savedActor.getMovies());

    }
}
