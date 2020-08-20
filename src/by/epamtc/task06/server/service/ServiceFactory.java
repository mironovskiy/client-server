package by.epamtc.task06.server.service;

import by.epamtc.task06.server.service.impl.TextServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final TextService textService = new TextServiceImpl();

    private ServiceFactory() {}

    public TextService getTextService() {

        return textService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }
}
