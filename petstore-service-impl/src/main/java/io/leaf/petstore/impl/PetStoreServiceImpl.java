package io.leaf.petstore.impl;

import io.leaf.petstore.api.Pet;
import io.leaf.petstore.api.PetStoreService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabo on 2015.07.23..
 */
public class PetStoreServiceImpl implements PetStoreService {

    private Vertx vertx;
    private List<Pet> petList = new ArrayList<>();

    public PetStoreServiceImpl(Vertx vertx) {
        this.vertx = vertx;
    }

    public void addPet(Pet pet, Handler<AsyncResult<JsonObject>> resultHandler) {
        System.out.println("Processing pet: "+pet.getName());
        petList.add(pet);
    }
}
