export interface HealthCheck {
    success: boolean
    lastsave: number
}

export const healthCheckDefaults = {
    pollingInterval: 10000, // milliseconds
    minutesToHoursThreshold: 120, // minutes
    warningSuccessThreshold: 24 // hours
}

export enum TimeIntervalType {SECONDS = 'seconds', MINUTES = 'minutes', HOURS = 'hours'}

export enum HealthCheckIcon {SUCCESS = 'check_circle', WARNING = 'warning'}

export enum HealthCheckClass {SUCCESS = 'up', WARNING = 'down', SUCESS_WARNING = 'up_old'}