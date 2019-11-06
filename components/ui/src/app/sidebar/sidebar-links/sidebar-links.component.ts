import {Component, Input, OnInit, QueryList, ViewChildren} from '@angular/core'
import {RfiService} from '../../services/rfi.service'
import {RfiLink} from '../../models/rfi-link.model'
import {Store} from '@ngrx/store'
import {AppState} from '../../state/appstate'
import {RecordItem, RecordType} from '../../models/record.model'
import {PrismService} from "../../services/prism.service";
import {RecordService} from "../../services/record.service";
import {SidebarExistingLinkComponent} from "../sidebar-existing-link/sidebar-existing-link.component";
import {FormGroup} from "@angular/forms";
import {RecordFormCreatorService} from "../record-form-creator/record-form-creator.service";

@Component({
    selector: 'stargate-sidebar-links',
    templateUrl: './sidebar-links.component.html',
    styleUrls: ['../sidebar.component.scss', '../form.scss', '/sidebar-links.component.scss']
})
export class SidebarLinksComponent implements OnInit {
    @Input()
    rfiId: number | string

    @Input()
    recordType: RecordType

    @Input()
    inputLinks: RfiLink[]

    links: RfiLink[]

    @ViewChildren(SidebarExistingLinkComponent)
    existingLinks !: QueryList<SidebarExistingLinkComponent>

    rfis: RecordItem[]
    nominations: RecordItem[]
    predictedLinks: RecordItem[]
    showLinks: Boolean = true
    showInputFields: Boolean = false
    showTypeAhead: Boolean = false
    linkForm: FormGroup

    constructor(
        private rfiService: RfiService,
        private recordService: RecordService,
        private prismService: PrismService,
        private store: Store<AppState>,
        private formCreator: RecordFormCreatorService
    ) {
        this.store.select('rfis').subscribe(rfis => {
            this.rfis = rfis
        })

        this.store.select('prismNominations').subscribe(nominations => {
            this.nominations = nominations
        })
    }

    ngOnInit() {
        this.initializePendingLink()
        this.links = Array.of(...this.inputLinks)
    }

    ngOnChanges() {
        this.links = Array.of(...this.inputLinks)
    }

    toggleLinksView() {
        this.showLinks = !this.showLinks
    }

    showInputs() {
        this.showInputFields = !this.showInputFields
        if (this.showInputFields) {
            this.initializePendingLink()
        }
    }

    onAdd() {
        const objectToLink = this.getObjectToLink()

        let existingLink = this.linkExists()
        if (!existingLink) {
            if (objectToLink) {
                this.links.push({...this.linkForm.value, title: objectToLink.title, status: objectToLink.status})
            }

            if (this.recordType === RecordType.RFI) {
                this.saveLinkToRfi()
            } else {
                this.saveLinkToNomination()
            }
        } else {
            this.existingLinks
                .find(component => component.link === existingLink)
                .highlight()
        }

        this.initializePendingLink()
        this.showInputs()
    }

    linkExists(): RfiLink {
        const pendingLink: RfiLink = this.linkForm.value
        return this.links.find((link: RfiLink) =>
            (link.record1Id === pendingLink.record1Id &&
                link.record2Id === pendingLink.record2Id &&
                link.record1System === pendingLink.record1System &&
                link.record2System === pendingLink.record2System) ||


            (link.record1Id === pendingLink.record2Id &&
                link.record2Id === pendingLink.record1Id &&
                link.record1System === pendingLink.record2System &&
                link.record2System === pendingLink.record1System)
        )
    }

    deleteLink(index: number) {
        const link: RfiLink = this.links[index]
        this.links = this.links.filter((value, idx) => idx !== index)

        this.recordService.deleteRecordLink(link)
    }

    openLinkedObject(link: RfiLink) {
        if (this.getRfiId() === link.record1Id) {
            this.fetchFromLink(link.record2Id, link.record2System)
        } else {
            this.fetchFromLink(link.record1Id, link.record1System)
        }
    }

    initializePendingLink() {
        let system
        if (this.recordType === RecordType.RFI) {
            system = 'IMM'
        } else {
            system = 'PRISM'
        }
        const blankLink = {record1Id: this.getRfiId(), record1System: system, record2Id: '', record2System: ''}
        this.linkForm = this.formCreator.createLinkForm(blankLink)
        this.linkForm.get('record2Id').disable()
        this.linkForm.get('record2System').valueChanges.subscribe(system => {
            if (system) {
                this.linkForm.get('record2Id').enable()
            } else {
                this.linkForm.get('record2Id').disable()

            }
        })
    }

    predictLinks(input: string, system: string) {
        if (input.length >= 1 && system === 'PRISM') {
            this.predictedLinks = this.nominations.filter(nom => {
                if (!this.rfiId) {
                    return nom.id.toLowerCase().includes(input.toLowerCase())
                } else {
                    return nom.id.toLowerCase().includes(input.toLowerCase())
                        && nom.id.toLowerCase() !== this.rfiId.toString().toLowerCase()
                }
            })
        } else if (input.length >= 1 && system === 'IMM') {
            this.showTypeAhead = true
            this.predictedLinks = this.rfis.filter(rfi => {
                if (!this.rfiId) {
                    return rfi.id.toString().includes(input)
                } else {
                    return rfi.id.toString().includes(input) && (rfi.id.toString() !== this.rfiId.toString())
                }
            })
        }
        if (this.predictLinks.length > 0)
            this.showTypeAhead = true
    }

    selectPredictedLink(id: string) {
        this.linkForm.patchValue({record2Id: id.toString()})
        this.showTypeAhead = false
    }

    closeTypeAhead() {
        this.showTypeAhead = false
    }

    private saveLinkToRfi() {
        this.recordService.saveRecordLink(this.linkForm.value)
    }

    private fetchFromLink(linkedRecordId: string, linkedRecordSystem: string) {
        if (linkedRecordSystem === 'IMM') {
            this.rfiService.fetchRfi(linkedRecordId)
        } else {
            this.prismService.fetchNominationById(linkedRecordId)
        }
    }

    private getObjectToLink(): RecordItem {
        switch (this.linkForm.value['record2System']) {
            case 'IMM':
                return this.rfis.find(rfi => rfi.id.toString() === this.linkForm.value['record2Id'])
            case 'PRISM':
                return this.nominations.find(nom => nom.id.toString() === this.linkForm.value['record2Id'])
            default:
                return null
        }
    }

    private getRfiId() {
        return !!this.rfiId ? this.rfiId.toString() : ''
    }

    private saveLinkToNomination() {
        this.recordService.saveRecordLink(this.linkForm.value)
    }
}
