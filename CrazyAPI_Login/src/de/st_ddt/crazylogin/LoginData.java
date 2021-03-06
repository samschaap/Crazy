package de.st_ddt.crazylogin;

import java.util.Date;

import de.st_ddt.crazyplugin.exceptions.CrazyCommandException;

public interface LoginData
{

	public abstract String getName();

	public abstract void setPassword(final String password) throws CrazyCommandException;

	public abstract boolean isPassword(final String password);

	public abstract void addIP(final String ip);

	public abstract boolean hasIP(final String ip);

	public abstract Date getLastActionTime();

	public abstract boolean isOnline();

	public abstract boolean login(final String password);

	public abstract void logout();

	public abstract void logout(final boolean removeIPs);

	public abstract void notifyAction();
}
