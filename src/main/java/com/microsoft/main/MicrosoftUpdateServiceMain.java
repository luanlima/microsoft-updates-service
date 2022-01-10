package com.microsoft.main;

import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.microsoft.task.MicrosoftUpdateTimerTask;

public class MicrosoftUpdateServiceMain {

    private static Logger logger = Logger.getLogger(MicrosoftUpdateServiceMain.class.getSimpleName());

    private static final long TIMER_TASK_INTERVAL_MILLISECONDS = 5 * 60 * 1000;

    public static void main(String[] args) {
	MicrosoftUpdateTimerTask microsoftUpdateTimerTask = new MicrosoftUpdateTimerTask();
	Timer timer = new Timer();

	/*
	 * Iniciando o timer task (delay de 1 segundo)
	 */
	timer.scheduleAtFixedRate(microsoftUpdateTimerTask, 1000, TIMER_TASK_INTERVAL_MILLISECONDS);

	logger.log(Level.INFO, "Task de consumo da API Microsoft agendada com sucesso.");
    }
}