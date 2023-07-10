package Repositorio;

import Entidades.TiendaVentas;
import Entidades.Usuario;

public class ValidacionUsuario {
    private TiendaVentas tienda;

    public ValidacionUsuario(TiendaVentas tienda) {
        this.tienda = tienda;
    }

    public boolean validarCredenciales(Usuario usuario, String contraseña) {
        if (tienda.getUsuariosRegistrados().containsKey(usuario.getCodigo())) {
            String contraseñaRegistrada = tienda.getUsuariosRegistrados().get(usuario.getCodigo()).getContraseña();
            return contraseña.equals(contraseñaRegistrada);
        }
        return false;
    }
}
