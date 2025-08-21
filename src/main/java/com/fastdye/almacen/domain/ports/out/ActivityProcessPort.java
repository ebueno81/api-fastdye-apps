package com.fastdye.almacen.domain.ports.out;

public interface ActivityProcessPort {
    int procesarActividad(int idActividad, String usuario);
}
