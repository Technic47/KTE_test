package ru.ktelabs.test.models.dto;

import ru.ktelabs.test.models.HumanModel;

public interface HumanModelCreator {
    <E extends HumanModel> E createHuman();
}
