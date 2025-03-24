package com.drivelab.autocenter.rest.vehicle;

public class VehicleDetailsResponseBody {

    private final String id;
    private final String plate;
    private final Model model;

    public VehicleDetailsResponseBody(String id, String plate, Model model) {
        this.id = id;
        this.plate = plate;
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public String getId() {
        return id;
    }

    public String getPlate() {
        return plate;
    }

    public static class Model {

        private final String name;

        public Model(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
