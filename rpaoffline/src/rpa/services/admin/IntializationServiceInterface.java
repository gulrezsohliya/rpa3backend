package rpa.services.admin;

import java.util.List;

import rpa.models.master.Cell;
import rpa.models.master.Office;
import rpa.models.master.User;

public interface IntializationServiceInterface {
	/* READ DATA */
	public Cell listCells(Integer officecode);

	public Office listOffice(String officecode);

	public User listUser(Integer usercode);

	public User listUser(String username);

	/* List */
	public List<User> listUser();

	public List<Cell> listCells();

	public List<Office> listOffices();

}
