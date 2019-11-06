export interface Sensor {
    id?: number
    rfiId: number
    sensor: string
    sensorType: string
    mode: string
    requiredQuality: number
    desiredQuality: number
}