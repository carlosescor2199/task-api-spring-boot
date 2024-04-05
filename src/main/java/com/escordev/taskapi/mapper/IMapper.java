package com.escordev.taskapi.mapper;

public interface IMapper <Input, Output>{
    Output map(Input input);
}
