package ch.unibe.ese.team1.controller.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.ese.team1.model.Location;

import com.jolbox.bonecp.BoneCPDataSource;

/**
 * Provides read access to the geo db. Performs the reading operations manually,
 * no ORM is involved.
 */
@Service
public class GeoDataService {

	@Autowired
	private BoneCPDataSource mainDataSource;

	/**
	 * Returns a list of all locations in the database.
	 */
	public List<Location> getAllLocations() {
		return executeQuery("SELECT zip.zip , zip.location, zip.lat, zip.lon FROM `zipcodes` zip");
	}

	/**
	 * Gets all locations that match the given city. The locations are ordered
	 * in ascending order in relation to the zip code.
	 * 
	 * @param city
	 *            the city to look for
	 * @return a list of all locations that match the given city
	 */
	public List<Location> getLocationsByCity(String city) {
		if (city.contains("\'")) {
			city = "";
		}
		return executeQuery("SELECT zip.zip , zip.location, zip.lat, zip.lon FROM `zipcodes` zip WHERE location = '"
				+ city + "' ORDER BY zip ASC;");

	}

	/**
	 * Gets all locations that have the given zipcode.
	 * 
	 * @param zipcode
	 *            the zipcode to search for
	 * @return a list of all locations that match
	 */
	public List<Location> getLocationsByZipcode(int zipcode) {
		return executeQuery("SELECT zip.zip, zip.location, zip.lat, zip.lon FROM `zipcodes` zip WHERE zip = "
				+ zipcode + ";");
	}

	private List<Location> executeQuery(String query) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Location> locationList = new ArrayList<>();
		try {
			connection = mainDataSource.getConnection();
			statement = connection.createStatement();

			resultSet = statement.executeQuery(query);
			locationList = writeResultSet(resultSet, locationList);

		} catch (SQLException ex) {
			System.out.println("Could not read locations from database.");
			ex.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (statement != null) {
					connection.close();
				}
			} catch (SQLException ex) {
				// ignore
			}
		}

		return locationList;
	}

	/**
	 * Fills the given list with the results from resultSet.
	 */
	private List<Location> writeResultSet(ResultSet resultSet,
			List<Location> locationList) throws SQLException {
		while (resultSet.next()) {
			int zip = resultSet.getInt("zip");
			String city = resultSet.getString("location");
			double latitude = resultSet.getDouble("lat");
			double longitude = resultSet.getDouble("lon");

			Location location = new Location();
			location.setZip(zip);
			location.setCity(city);
			location.setLatitude(latitude);
			location.setLongitude(longitude);

			locationList.add(location);
		}

		return locationList;
	}
}
