package ac.tec.ic6821.ej4.controllers

import ac.tec.ic6821.ej4.model.City
import ac.tec.ic6821.ej4.model.Road

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
abstract class BaseController {

    protected Map cityToJson(City city) { [rid: city.id as String, name: city.name] }

    protected Map roadToJson(Road road) {
        [
            rid: road.id as String,
            name: road.name,
            distanceKms: road.distanceKms,
            from: cityToJson(road.out),
            to: cityToJson(road.in)
        ]
    }
}
