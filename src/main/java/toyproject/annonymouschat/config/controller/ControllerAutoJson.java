package toyproject.annonymouschat.config.controller;

public interface ControllerAutoJson<E> {
    Object process(E requestBody);
}
