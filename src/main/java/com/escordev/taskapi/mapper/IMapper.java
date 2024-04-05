package com.escordev.taskapi.mapper;

public interface IMapper <Input, Output>{
    public Output map(Input input);
}
