package com.eliasnepo.motosport.application.components;

import com.eliasnepo.motosport.infraestructure.user.jpa.UserEntity;

public interface IAuthComponent {

    UserEntity authenticated();
}
