package ru.mtsbank.fintech.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import ru.mts.animals_creators.AnimalFactory;
import ru.mts.animals_creators.CreateAnimalServiceImpl;

import java.util.Random;

@Configuration
public class CreateAnimalServiceBeanPostProcessor implements BeanPostProcessor {
    /**
     * <b>postProcessBeforeInitialization</b>
     * @param bean the new bean instance
     * @param beanName the name of the bean
     * Если созданный бин это createanimalservicelmpl, то заполняет поле animalType случайным типом
     * @return bean
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equalsIgnoreCase("createanimalserviceimpl")) {
            CreateAnimalServiceImpl beanBuf = (CreateAnimalServiceImpl) bean;
            beanBuf.setAnimalType(AnimalFactory.AnimalType.values()[new Random().nextInt(3)]);


        }

        return bean;
    }
    /**
     * <b>postProcessAfterInitialization</b>
     * @param bean the new bean instance
     * @param beanName the name of the bean
     * @return bean
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
