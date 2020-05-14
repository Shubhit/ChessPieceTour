package com.sample.project.chesstour.service;

public class OutputService {

    private static OutputService instance = new OutputService();

    private OutputService() {}

    public static OutputService getInstance() {

        return instance;

    }

    /**
     * @param lineOfMessage string to be displayed as a single line
     */
    public void output(String lineOfMessage) {

        System.out.println(lineOfMessage);

    }

}
