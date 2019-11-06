import {Component, EventEmitter, Input, OnChanges, Output} from '@angular/core'
import {RecordItem, RecordType, trimStringLength} from '../../models/record.model'
import {RfiService} from '../../services/rfi.service'
import {PrismService} from '../../services/prism.service'
import {animate, style, transition, trigger} from "@angular/animations";

@Component({
    selector: 'sidebar-map-list',
    templateUrl: './sidebar-map-list.component.html',
    styleUrls: ['./sidebar-map-list.component.scss'],
    animations: [
        trigger('slideInOut', [
            transition(':enter', [
                style({transform: 'translateX(100%)'}),
                animate('200ms ease-in', style({transform: 'translateX(0%)'}))
            ]),
            transition(':leave', [
                animate('200ms ease-in', style({transform: 'translateX(100%)'}))
            ])
        ])
    ]
})
export class SidebarMapListComponent implements OnChanges {
    @Input()
    combinedList: RecordItem[]

    @Input()
    activeRecordIds: string[]

    @Input()
    mapLoading: boolean

    @Output()
    revealLocation: EventEmitter<string> = new EventEmitter<string>()

    @Output()
    constrainResults: EventEmitter<boolean> = new EventEmitter<boolean>()

    checked: boolean = false
    showSidebar: boolean = true

    constructor(private rfiService: RfiService, private prismService: PrismService) {
    }

    ngOnChanges(changes) {
        if (changes && changes.activeRecordIds && changes.activeRecordIds.currentValue.length > 0) {
            const element = document.getElementById(changes.activeRecordIds.currentValue[0])
            if (element) {
                element.scrollIntoView({behavior: 'smooth'})
            }
        }
    }

    getTrimmedString(value: string, maxLength: number) {
        return trimStringLength(value, maxLength)
    }

    openRecordSidebar(record: RecordItem) {
        if (record.recordType === RecordType.RFI) {
            this.rfiService.fetchRfi(record.id)
        } else {
            this.prismService.fetchNominationById(record.id)
        }
    }

    fireRevealLocation(id) {
        this.revealLocation.emit(id)
    }

    doConstrainResults() {
        if (!this.mapLoading) {
            this.checked = !this.checked
            this.constrainResults.emit(this.checked)
        }
    }
}