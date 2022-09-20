package com.cda.contenu_seance.validation;

import com.cda.contenu_seance.DTO.IntervenantDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        IntervenantDTO intervenantDTO = (IntervenantDTO) obj;
        return intervenantDTO.getMp().equals(intervenantDTO.getMatchingPassword());
    }
}
