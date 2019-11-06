import {Component, EventEmitter, Input, Output} from '@angular/core'
import {
    HealthCheckClass,
    healthCheckDefaults, HealthCheckIcon,
    TimeIntervalType
} from "../../../models/health-check.model";
import {HttpClient} from "@angular/common/http";
import {getDataForRecordType} from "../../../models/record.model";
import {HealthCheck} from "../../../models/health-check.model";
import {FilterOption} from "../../../models/select-option.model";

@Component({
    selector: 'stargate-filter-system-option',
    templateUrl: './filter-system-option.component.html',
    styleUrls: ['./filter-system-option.component.scss', '../../filter/filter.component.scss']
})
export class FilterSystemOptionComponent {
    @Input()
    option: FilterOption

    @Input()
    isChecked: boolean

    @Output()
    updateFilter: EventEmitter<FilterOption> = new EventEmitter<FilterOption>()

    label: string
    icon: HealthCheckIcon = HealthCheckIcon.WARNING
    className: HealthCheckClass = HealthCheckClass.WARNING
    lastSaveText: string
    loading: boolean = true

    private basePath = '/api/v1/health'

    constructor(private httpClient: HttpClient) {}

    ngOnInit() {
        this.label = getDataForRecordType(this.option.system, 'filterLabel')
        this.callIntegrationHealthChecks()
    }

    callIntegrationHealthChecks = () => {
        const path = getDataForRecordType(this.option.system, 'component').toLowerCase()
        this.httpClient.get(`${this.basePath}/${path}`)
            .subscribe((resp: HealthCheck) => {
                    this.icon = HealthCheckIcon.WARNING
                    this.className = HealthCheckClass.WARNING
                    if (resp.success) {
                        this.className = HealthCheckClass.SUCCESS
                        this.icon = HealthCheckIcon.SUCCESS
                    }

                    this.lastSaveText = undefined
                    if (resp.lastsave !== -1) {
                        let interval = TimeIntervalType.MINUTES
                        let timeDiff = Math.floor((resp.lastsave / 1000) /60)
                        if (timeDiff > healthCheckDefaults.minutesToHoursThreshold ) {
                            timeDiff =  Math.floor(timeDiff / 60)
                            interval = TimeIntervalType.HOURS
                            if (timeDiff > healthCheckDefaults.warningSuccessThreshold) {
                                this.className = HealthCheckClass.SUCESS_WARNING
                            }
                        }
                        this.lastSaveText = `${timeDiff} ${interval} old`
                    }
                    this.loading = false
                },
                () => {
                    this.className = HealthCheckClass.WARNING
                    this.icon = HealthCheckIcon.WARNING
                    this.lastSaveText = undefined
                    this.loading = false
                })
    }

    toggleFilter() {
        this.updateFilter.emit(this.option)
    }
}
