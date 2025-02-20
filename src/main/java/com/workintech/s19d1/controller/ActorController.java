package com.workintech.s19d1.controller;


import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.dto.ActorResponse;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.ActorService;
import com.workintech.s19d1.util.ActorConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.workintech.s19d1.util.ActorConverter.convertActorToActorResponse;

@RestController
@RequestMapping("/actor")

public class ActorController {


    private ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }



    @GetMapping
    public List<ActorResponse> getAllActors() {
        List<Actor> allActors = actorService.findAll();
        return ActorConverter.convertActorCollectionToActorResponseCollection(allActors);
    }

    @GetMapping("/{id}")
    public ActorResponse getActorById(@PathVariable("id") Long id) {
        Actor foundActor = actorService.findById(id);
        return convertActorToActorResponse(foundActor);
    }

    @PostMapping
    public ActorResponse saveActorAndMovie(@RequestBody ActorRequest actorRequest) {

        Actor actor = actorRequest.getActor();
        List<Movie> movies = actorRequest.getMovies();

        for (Movie movie : movies) {
            actor.addMovie(movie);
        }

        Actor savedActor = actorService.save(actor);
        return ActorConverter.convertActorResponseBodyToActorResponse(savedActor);
    }


    @PutMapping("/{id}")
    public ActorResponse update(@RequestBody Actor actor, @PathVariable Long id) {
        Actor foundActor = actorService.findById(id);
        actor.setMovies(foundActor.getMovies());
        actor.setId(foundActor.getId());
        actorService.save(actor);
        return ActorConverter.convertActorToActorResponse(foundActor);
    }

    @DeleteMapping("/{id}")
    public ActorResponse delete(@PathVariable("id") Long id) {
        Actor foundActor = actorService.findById(id);
        actorService.delete(foundActor);
        return ActorConverter.convertActorToActorResponse(foundActor);
    }
}
