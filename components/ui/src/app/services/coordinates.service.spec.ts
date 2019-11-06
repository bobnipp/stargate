import {CoordinatesService} from './coordinates.service'

describe('CoordinatesService', () => {
    const subject = new CoordinatesService()

    it('getLatLng returns an array of the lat and long, supports various input formats', () => {
        expect(subject.getLatLng('30.2345 140.2345')).toEqual([30.2345, 140.2345])
        expect(subject.getLatLng('-30.2345 -140.2345')).toEqual([-30.2345, -140.2345])
        expect(subject.getLatLng('30.2345N 140.2345E')).toEqual([30.2345, 140.2345])
        expect(subject.getLatLng('30.2345S 140.2345W')).toEqual([-30.2345, -140.2345])
        expect(subject.getLatLng('385959N 1035959E')).toEqual([38.999722222222225, 103.99972222222222])
    })

    it('getLngLat returns an array of the long and lat', () => {
        const result = subject.getLngLat('39.7628904 -105.0108871')
        expect(result).toEqual([-105.0108871, 39.7628904])
    })

    it('returns null if the coordinates are invalid', () => {
        expect(subject.getLatLng(null)).toEqual(null)
        expect(subject.getLatLng('')).toEqual(null)
        expect(subject.getLatLng('39.455')).toEqual(null)
        expect(subject.getLatLng('34.5, 56.2, -123.4')).toEqual(null)
    })
})