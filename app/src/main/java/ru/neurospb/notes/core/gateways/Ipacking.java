package ru.neurospb.notes.core.gateways;

/**
 * An interface to parse Data Objects between different layers.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public interface Ipacking<Tout, Tin> {
    /**
     * Packs Data Object from one layer to use in other layer.
     * @param model2parse A model to be parsed.
     * @return A required model.
     */
    Tout pack(Tin model2parse);
}