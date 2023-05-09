package ru.ilvladik.shelterforcats.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ilvladik.shelterforcats.models.Application;
import ru.ilvladik.shelterforcats.services.ApplicationsService;

@Component
public class ApplicationValidator implements Validator {

    private final ApplicationsService applicationsService;

    @Autowired
    public ApplicationValidator(ApplicationsService applicationsService) {
        this.applicationsService = applicationsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Application.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Application application = (Application) target;
        if (applicationsService.getApplicationByPhone(application.getPhone()) != null) {
            errors.rejectValue("phone", "", "Заявка с таким номером телефона уже существует");
        }
        String name = application.getName();
        if (name == null || name.length() < 2 || name.length() > 100) {
            errors.rejectValue("name", "", "Имя должно содержать от 2 до 100 символов");
        }
        String phone = application.getPhone();
        if (phone == null || !phone.matches("(\\+7)\\d{10}")) {
            errors.rejectValue("phone", "", "Неверный номер телефона");
        }
    }
}
