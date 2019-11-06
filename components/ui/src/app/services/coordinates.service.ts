import {Injectable} from '@angular/core'
import {CoordinateValidators} from '../validators/custom-validators'

@Injectable()
export class CoordinatesService {
    public getLatLng(coordinates: string): Array<number> {
        if (!coordinates || !CoordinateValidators.validateCoordinateString(coordinates)) {
            return null
        }

        const coordinatesArray = coordinates.split(' ')
        let latLng: number[] = []

        latLng.push(this.parseLatitude(coordinatesArray[0]))
        latLng.push(this.parseLongitude(coordinatesArray[1]))

        return latLng
    }

    public getLngLat(coordinates: string): Array<number> {
        const latLon = this.getLatLng(coordinates)
        return latLon ? latLon.reverse() : null
    }

    private parseLatitude(lat) {
        const parseDMS = function (s: string) {
            const degrees = parseInt(s.substring(0, 2))
            const minutes = parseInt(s.substring(2, 4))
            const seconds = parseInt(s.substring(4, 6))
            return degrees + (minutes / 60.0) + (seconds / 3600.0)
        }

        if (lat.charAt(lat.length - 1) === 'N') {
            let latNum = parseFloat(lat.substr(0, lat.length - 1))
            if (latNum > 90) {
                latNum = parseDMS(lat)
            }
            return latNum
        } else if (lat.charAt(lat.length - 1) === 'S') {
            let latNum = parseFloat(lat.substr(0, lat.length - 1))
            if (latNum > 90) {
                latNum = parseDMS(lat)
            }
            return latNum * -1
        } else {
            return parseFloat(lat)
        }
    }

    private parseLongitude(lng) {
        const parseDMS = function (s: string) {
            const degrees = parseInt(s.substring(0, 3))
            const minutes = parseInt(s.substring(3, 5))
            const seconds = parseInt(s.substring(5, 7))
            return degrees + (minutes / 60.0) + (seconds / 3600.0)
        }

        if (lng.charAt(lng.length - 1) === 'E') {
            let lngNum = parseFloat(lng.substr(0, lng.length - 1))
            if (lngNum > 180) {
                lngNum = parseDMS(lng)
            }
            return lngNum
        } else if (lng.charAt(lng.length - 1) === 'W') {
            let lngNum = parseFloat(lng.substr(0, lng.length - 1))
            if (lngNum > 180) {
                lngNum = parseDMS(lng)
            }
            return lngNum * -1
        } else {
            return parseFloat(lng)
        }
    }
}