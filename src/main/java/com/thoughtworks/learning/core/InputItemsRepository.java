package com.thoughtworks.learning.core;

import java.util.List;
import java.util.Map;

/**
 * Created by yan on 16-3-11.
 */
public interface InputItemsRepository {
    List<InputItems> findInputItems();

    InputItems newInputItems(String barcode);

    void createInputItems(Map newInstance);

    InputItems getInputItemsById(int inputItemsId);
}
