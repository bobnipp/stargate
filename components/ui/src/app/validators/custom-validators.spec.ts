import {CoordinateValidators} from './custom-validators'
import {FormControl, FormGroup} from '@angular/forms'
import {RecordFormCreatorService} from "../sidebar/record-form-creator/record-form-creator.service";
import {RecordItem} from "../models/record.model";

describe('CustomValidators', () => {

    const invalidCoordsInputError = {invalidCoordinates: CoordinateValidators.INVALID_COORDINATES_MESSAGE}
    const invalidCoordsTargetTypeError = {invalidNumberOfCoordinates: CoordinateValidators.INVALID_NUMBER_OF_COORDINATES_MESSAGE}
    const invalidBasicCoordsError = {invalidBasicCoordinates: CoordinateValidators.INVALID_BASIC_COORDINATES_MESSAGE}

    describe('validateCoordinates', () => {
        it('validates base coordinates', () => {
            let rfiForm = new RecordFormCreatorService().createRecordForm({} as RecordItem)
            let coordinates = rfiForm.get('coordinates') as FormControl
            expect(CoordinateValidators.validateCoordinates(coordinates)).toBeNull()
            coordinates.setValue('10 10; -10.0 -10.0; -10.00 10.00; 10.000 -10.000')
            expect(CoordinateValidators.validateCoordinates(coordinates)).toBeNull()
            coordinates.setValue('10.0000n 10.0000e, 10.00000N 10.00000E; 10.000000s 10.000000w; 10.0000000S 10.0000000W')
            expect(CoordinateValidators.validateCoordinates(coordinates)).toBeNull()
            coordinates.setValue('000000N 0000000W, 900000n 1800000w; 000000s 0000000e; 900000S 1800000W')
            expect(CoordinateValidators.validateCoordinates(coordinates)).toBeNull()
            coordinates.setValue('000001N 0000001W, 895959n 1795959w; 344554s 1763454e; 112344S 1674323W')
            expect(CoordinateValidators.validateCoordinates(coordinates)).toBeNull()

            coordinates.setValue('10. 10')
            expect(CoordinateValidators.validateCoordinates(coordinates)).toEqual(invalidCoordsInputError)
            coordinates.setValue('90.1 10')
            expect(CoordinateValidators.validateCoordinates(coordinates)).toEqual(invalidCoordsInputError)
            coordinates.setValue('90 180.1')
            expect(CoordinateValidators.validateCoordinates(coordinates)).toEqual(invalidCoordsInputError)

            coordinates.setValue('10.n 10w')
            expect(CoordinateValidators.validateCoordinates(coordinates)).toEqual(invalidCoordsInputError)
            coordinates.setValue('90.1n 10w')
            expect(CoordinateValidators.validateCoordinates(coordinates)).toEqual(invalidCoordsInputError)
            coordinates.setValue('90n 180.1w')
            expect(CoordinateValidators.validateCoordinates(coordinates)).toEqual(invalidCoordsInputError)

            coordinates.setValue('900N 1800W')
            expect(CoordinateValidators.validateCoordinates(coordinates)).toEqual(invalidCoordsInputError)
            coordinates.setValue('900001N 1800000W')
            expect(CoordinateValidators.validateCoordinates(coordinates)).toEqual(invalidCoordsInputError)
            coordinates.setValue('900000N 1800001W')
            expect(CoordinateValidators.validateCoordinates(coordinates)).toEqual(invalidCoordsInputError)
            coordinates.setValue('595959N 1805959W')
            expect(CoordinateValidators.validateCoordinates(coordinates)).toEqual(invalidCoordsInputError)
            coordinates.setValue('-000000N -0000000W')
            expect(CoordinateValidators.validateCoordinates(coordinates)).toEqual(invalidCoordsInputError)

        })
    })

    describe('validateTargetCoordinates', () => {
        let form: FormGroup

        beforeEach(() => {
            form = new FormGroup({
                type: new FormControl(''),
                coordinates: new FormControl('')
            }, {validators: CoordinateValidators.validateTargetCoordinates})
        })

        it('validates POINT target coordinates', () => {
            form.get('type').setValue('POINT')
            form.get('coordinates').setValue('10.0 10.0')
            expect(CoordinateValidators.validateTargetCoordinates(form)).toBeNull()

            form.get('coordinates').setValue('10.0 10.0, 20 20')
            expect(CoordinateValidators.validateTargetCoordinates(form)).toEqual(invalidCoordsTargetTypeError)
        })

        it('validates AREA target coordinates', () => {
            form.get('type').setValue('AREA')
            form.get('coordinates').setValue('10.0 10.0, 20 20, 30 30')
            expect(CoordinateValidators.validateTargetCoordinates(form)).toBeNull()

            form.get('coordinates').setValue('10.0 10.0, 20 20')
            expect(CoordinateValidators.validateTargetCoordinates(form)).toEqual(invalidCoordsTargetTypeError)
        })

        it('validates LOC target coordinates', () => {
            form.get('type').setValue('LOC')
            form.get('coordinates').setValue('10.0 10.0, 20 20')
            expect(CoordinateValidators.validateTargetCoordinates(form)).toBeNull()

            form.get('coordinates').setValue('10.0 10.0, 20 20, 30 30')
            expect(CoordinateValidators.validateTargetCoordinates(form)).toEqual(invalidCoordsTargetTypeError)
        })

        it('validates DSA target coordinates', () => {
            form.get('type').setValue('DSA')
            form.get('coordinates').setValue('10.0 10.0, 20 20, 30 30')
            expect(CoordinateValidators.validateTargetCoordinates(form)).toBeNull()

            form.get('coordinates').setValue('10.0 10.0, 20 20')
            expect(CoordinateValidators.validateTargetCoordinates(form)).toEqual(invalidCoordsTargetTypeError)
        })
    })

    describe('validateBasicCoordinates', () => {
        let rfiForm = new RecordFormCreatorService().createRecordForm({} as RecordItem)
        let coordinates = rfiForm.get('coordinates') as FormControl

        it('validates there is only one coordinate', () => {
            expect(CoordinateValidators.validateBasicCoordinate(coordinates)).toBeNull()
            coordinates.setValue('10 10; -10.0')
            expect(CoordinateValidators.validateBasicCoordinate(coordinates)).toEqual(invalidBasicCoordsError)
            coordinates.setValue('10 10, -10.0')
            expect(CoordinateValidators.validateBasicCoordinate(coordinates)).toEqual(invalidBasicCoordsError)
        })
    })

    it('separateCoordinates', () => {
        let coords = '12.12 12.10; 12.34 12, 23 56, 23 65'
        const expectedCoords = [
            '12.12 12.10',
            '12.34 12',
            '23 56',
            '23 65'
        ]
        expect(CoordinateValidators.separateCoordinates(coords)).toEqual(expectedCoords)
    })
})
