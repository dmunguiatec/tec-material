package ac.tec.ic6821.ej4.controllers

import ac.tec.ic6821.ej4.model.City
import ac.tec.ic6821.ej4.model.Road
import ac.tec.ic6821.ej4.services.RoadMapService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

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
@RestController
@RequestMapping('/roadmap')
class RoadMapController extends BaseController {

    @Autowired
    private RoadMapService roadMapService

    @RequestMapping(value = '/batch', method = RequestMethod.POST)
    @ResponseBody
    Map batchPost(@RequestBody Map batch) {
        roadMapService.saveBatch(batch)
        return [status: 200]
    }

    @RequestMapping(value = '/city', method = RequestMethod.GET)
    @ResponseBody
    def cityGet() {
        // la conversión directa de City a JSON no es posible debido a la implementación interna de City
        roadMapService.cities.collect { City city -> cityToJson(city) }
    }

    @RequestMapping(value = '/road', method = RequestMethod.GET)
    @ResponseBody
    def roadGet() {
        roadMapService.roads.collect { Road road -> roadToJson(road) }
    }
}
