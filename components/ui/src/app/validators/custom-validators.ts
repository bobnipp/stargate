import {FormControl, FormGroup, ValidationErrors, Validators} from '@angular/forms'

export class CoordinateValidators extends Validators {

    static readonly VALID_DEGREES = /^((90[0]{4}[N,S,n,s])|([0-8][0-9][0-5][0-9][0-5][0-9][N,S,n,s]))([ ])((180[0]{4}[E,W,e,w])|([0-1][0-7][0-9][0-5][0-9][0-5][0-9][E,W,e,w]))$/
    static readonly VALID_CARDINAL = /^((90(\.[0]{1,7})?[N,S,n,s])|([0-8]?[0-9](\.[0-9]{1,7})?)[N,S,n,s])([ ])((180(\.[0]{1,7})?[E,W,e,w])|(((([0-1]?[0-7])|[0-9])?[0-9](\.[0-9]{1,7})?)[E,W,e,w]))$/
    static readonly VALID_PLUS_MINUS = /^((-?90(\.[0]{1,7})?)|(-?[0-8]?[0-9](\.[0-9]{1,7})?))([ ])((-?180(\.[0]{1,7})?)|(-?((([0-1]?[0-7])|[0-9])?[0-9](\.[0-9]{1,7})?)))$/

    static readonly INVALID_NUMBER_OF_COORDINATES_MESSAGE = 'Does not match Target Type. Point (1 vertex); LOC (min. of 2); Area or DSA (min. of 3)'
    static readonly INVALID_COORDINATES_MESSAGE = 'Coordinates are invalid, check for spaces and that vertices are separated by a comma'
    static readonly INVALID_BASIC_COORDINATES_MESSAGE = 'Only 1 vertex allowed'

    static validateCoordinates(control: FormControl): ValidationErrors {
        return CoordinateValidators.validateCoordinateString(control.value) ? null : {invalidCoordinates: CoordinateValidators.INVALID_COORDINATES_MESSAGE}
    }

    static validateTargetCoordinates(control: FormGroup): ValidationErrors {
        const coordsArr = CoordinateValidators.separateCoordinates(control.get('coordinates').value)
        const coordsErr = { invalidNumberOfCoordinates: CoordinateValidators.INVALID_NUMBER_OF_COORDINATES_MESSAGE }

        if (coordsArr.length === 1 && coordsArr[0] === '') {
            return null
        }

        let value = control.get('type').value
        if (value === 'POINT' && coordsArr.length !== 1) {
            return coordsErr
        } else if (value === 'LOC' && coordsArr.length !== 2) {
            return coordsErr
        } else if ((value === 'AREA' || value === 'DSA') && coordsArr.length < 3) {
            return coordsErr
        }

        return null
    }

    static validateCoordinateString(coord: string): boolean {
        if (coord) {
            return CoordinateValidators.separateCoordinates(coord)
                .filter(c => !c.match(CoordinateValidators.VALID_DEGREES))
                .filter(c => !c.match(CoordinateValidators.VALID_CARDINAL))
                .filter(c => !c.match(CoordinateValidators.VALID_PLUS_MINUS))
                .length === 0
        }
        return true
    }

    static validateBasicCoordinate(control: FormControl): ValidationErrors {
        return CoordinateValidators.separateCoordinates(control.value).length == 1 ? null : {invalidBasicCoordinates: CoordinateValidators.INVALID_BASIC_COORDINATES_MESSAGE}
    }

    static separateCoordinates(coordinates: string) {
        return coordinates === null ? [] : coordinates.split(/;|,/).map(coord => coord.trim())
    }
}


