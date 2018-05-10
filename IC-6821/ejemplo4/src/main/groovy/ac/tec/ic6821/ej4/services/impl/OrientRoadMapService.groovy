package ac.tec.ic6821.ej4.services.impl

import ac.tec.ic6821.ej4.model.City
import ac.tec.ic6821.ej4.model.Road
import ac.tec.ic6821.ej4.services.RoadMapService
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 *   ejemplo4
 *   Copyright (C) 2015  diegomunguia
 *
 *   This program is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation; either version 2 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License along
 *   with this program; if not, write to the Free Software Foundation, Inc.,
 *   51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
@Service
class OrientRoadMapService implements RoadMapService {

    @Autowired
    OrientGraphFactory graphFactory

    @Override
    void saveBatch(Map batch) {

        // TODO: validation of the batch map structure

        def persistedCities = [:]

        graphFactory.withTransaction {
            // TODO: duplicate prevention validations
            batch.cities?.each { city ->
                persistedCities[city.name] = new City(name: city.name)
            }

            // TODO: duplicate prevention validations
            batch.roads?.each { road ->
                City from = persistedCities[road.from]
                City to = persistedCities[road.to]

                Road persistedRoad = from.addToNeighborCities(to)
                persistedRoad.name = road.name
                persistedRoad.distanceKms = road.distanceKms

                persistedRoad = to.addToNeighborCities(from)
                persistedRoad.name = road.name
                persistedRoad.distanceKms = road.distanceKms
            }
        }
    }

    @Override
    List<City> getCities() {
        return graphFactory.withTransaction {
            return City.graphQuery('select from City')
        }
    }

    @Override
    List<Road> getRoads() {
        return graphFactory.withTransaction {
            return Road.graphQuery('select from Road')
        }
    }
}
